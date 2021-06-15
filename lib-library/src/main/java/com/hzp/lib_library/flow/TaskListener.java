package com.hzp.lib_library.flow;


import com.hzp.lib_library.flow.Task;

public interface TaskListener {

    void onStart(Task task);

    void onRunning(Task task);

    void onFinish(Task task);
}

