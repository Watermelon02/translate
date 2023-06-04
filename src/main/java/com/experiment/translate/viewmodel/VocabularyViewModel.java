package com.experiment.translate.viewmodel;

import com.experiment.lib_react.ObservableOnSubscribe;
import com.experiment.lib_react.emitter.ObservableEmitter;
import com.experiment.lib_react.observable.flow.Flow;
import com.experiment.lib_react.observable.Observable;
import com.experiment.lib_react.observer.OnNextAction;
import com.experiment.lib_react.observer.OnNextObserver;
import com.experiment.lib_react.scheduler.MainThreadScheduler;
import com.experiment.lib_react.scheduler.NewThreadScheduler;
import com.experiment.translate.MainApp;
import com.experiment.translate.helper.SignGenerator;
import com.experiment.translate.repository.bean.Word;
import com.experiment.translate.repository.bean.WordSet;
import com.experiment.translate.repository.bean.YoudaoTranslationResponse;
import com.experiment.translate.repository.local.database.TranslateDatabase;
import com.experiment.translate.repository.remote.retrofit.YoudaoServiceCreator;

import java.util.List;

public class VocabularyViewModel implements ViewModel {
    TranslateDatabase database = TranslateDatabase.getInstance();
    //当前选中的单词集
    public final Flow<WordSet> currentWordSet = new Flow<>();
    //当前选中的单词
    public final Flow<Word> currentWord = new Flow<>();

    public final Flow<List<WordSet>> wordSetList = new Flow<>();
    public final Flow<Word> newWord = new Flow<>();


    @Override
    public void init() {
    }

    public void fetchWordSet() {
        Observable.create(new ObservableOnSubscribe<List<WordSet>>() {
            @Override
            public void subscribe(ObservableEmitter<List<WordSet>> observableEmitter) {
                List<WordSet> wordSets = database.getUserDAO().getWordSetByUserId(MainApp.userId);
                observableEmitter.onNext(wordSets);
            }
        }).subscribeOn(new NewThreadScheduler()).observeOn(new MainThreadScheduler()).subscribe(new OnNextObserver<List<WordSet>>() {
            @Override
            public void onNext(List<WordSet> wordSets) {
                wordSetList.onNext(wordSets);
            }
        });
    }

    public void fetchWord(String wordsText, WordSet wordSet) {
        String[] words = wordsText.split(",");
        for (String word : words) {
            long curtime = System.currentTimeMillis() / 1000; // 获取当前时间戳（秒级）
            String curtimeText = String.valueOf(curtime);

            String sign = SignGenerator.generateYoudaoSign(word, curtimeText);
            YoudaoServiceCreator.getYoudaoTranslateService().translate(word, sign, curtimeText).subscribeOn(new NewThreadScheduler()).doOnNext(new OnNextAction<YoudaoTranslationResponse>() {
                @Override
                public void run(YoudaoTranslationResponse s) {
                    if (s.getBasic() != null) {
                        //插入新单词到数据库
                        Word newWord = new Word(s.getQuery(), s.getSpeakUrl(), s.getBasic().getPhonetic(), s.getBasic().getUkSpeech(), s.getBasic().getUsSpeech());
                        newWord.setExplanation(s.getBasic().getExplains());
                        database.getWordDAO().insertWord(newWord);
                        for (Word existWord : wordSet.getWordList()) {//防止重复插入同一单词到同一单词本中
                            if (existWord.getWord_id().equals(newWord.getWord_id())) return;
                        }
                        database.getWordSetDAO().insertWordIntoWordSet(newWord, wordSet);
                    }
                }
            }).observeOn(new MainThreadScheduler()).subscribe(new OnNextObserver<YoudaoTranslationResponse>() {

                @Override
                public void onNext(YoudaoTranslationResponse s) {
                    if (s.getBasic() != null) {
                        //刷新内存中的单词集
                        Word newWord = new Word(s.getQuery(), s.getSpeakUrl(), s.getBasic().getPhonetic(), s.getBasic().getUkSpeech(), s.getBasic().getUsSpeech());
                        newWord.setExplanation(s.getBasic().getExplains());
                        VocabularyViewModel.this.newWord.onNext(newWord);
                    }
                }
            });
        }
    }

    public void removeWordFromWordSet(int index, WordSet wordSet) {
        Observable.create(new ObservableOnSubscribe<WordSet>() {
            @Override
            public void subscribe(ObservableEmitter<WordSet> observableEmitter) {
                //在子线程中从数据库中删除联系
                database.getWordSetDAO().deleteWordFromWordSet(wordSet.getWordList().get(index), wordSet);
            }
        }).subscribeOn(new NewThreadScheduler()).observeOn(new MainThreadScheduler()).subscribe(new OnNextObserver<WordSet>() {
            @Override
            public void onNext(WordSet wordSet) {
            }
        });
    }
}
