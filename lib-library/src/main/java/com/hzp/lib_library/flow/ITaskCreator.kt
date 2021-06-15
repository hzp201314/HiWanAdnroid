package com.hzp.lib_library.flow

interface ITaskCreator {
    fun createTask(taskName: String): Task
}