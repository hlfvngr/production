package com.cskaoyan.erp.service;

import com.cskaoyan.erp.bean.Task;
import com.cskaoyan.erp.utils.PageModel;

import java.util.List;

public interface TaskService {

    List<Task> findAllTask();

    List<Task> findAllTask(Task task, PageModel pageModel);

    boolean insertTask(Task task);

    boolean updateTask(Task task);

    boolean deleteTask(String[] ids);

    Task findTaskById(String taskId);
}
