package com.experiment.translate.helper;

import com.experiment.translate.viewmodel.ViewModel;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public class ViewModelController {
    private static ViewModelController instance;
    //建立一个专门存储viewModel的Map，用于缓存子view的viewModel
    private final HashMap<String, ViewModel> childrenViewModel = new HashMap<String, ViewModel>();
    private final HashMap<String, Boolean> childrenHaveViewModel = new HashMap<String, Boolean>();

    private ViewModelController() {
    }

    public static ViewModelController getInstance() {
        if (instance == null) {
            synchronized (ViewController.class) {
                if (instance == null) {
                    instance = new ViewModelController();
                }
            }
        }
        return instance;
    }

    // 尝试反射加载视图对应的ViewModel
    public void tryGenerateViewModel(String name) {
        try {
            ViewModel viewModel;
            Class.forName("com.experiment.translate.viewmodel." + name + "Model");
            viewModel = (ViewModel) Class.forName("com.experiment.translate.viewmodel." + name + "Model").getDeclaredConstructor().newInstance();
            viewModel.init();
            childrenViewModel.put(name, viewModel);
            childrenHaveViewModel.put(name, true);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException e) {
            childrenHaveViewModel.put(name, false);
            throw new RuntimeException(e);
        }
    }

    public ViewModel getViewModel(String name) {
        Boolean haveViewModel = childrenHaveViewModel.get(name);
        if (haveViewModel == null) {
            tryGenerateViewModel(name);
            haveViewModel =childrenHaveViewModel.get(name);
        }
        if (Boolean.TRUE.equals(haveViewModel)) {
            return childrenViewModel.get(name);
        } else return null;
    }
}
