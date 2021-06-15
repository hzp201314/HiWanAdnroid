package com.hzp.lib_library.taskflow

/**
 * 创建具体的任务
 */
interface ITaskCreator {
    fun createTask(taskName: String): Task
}