package com.zeeshan.roomdatabase;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ViewModal extends AndroidViewModel
{
    private CourseRepository repository;

    private LiveData<List<CourseModal>> allCourses;

    public ViewModal(@NonNull Application application)
    {
        super(application);
        repository = new CourseRepository(application);
        allCourses = repository.getAllCourses();
    }

    public void insert(CourseModal model)
    {
        repository.insert(model);
    }

    public void update(CourseModal model)
    {
        repository.update(model);
    }

    public void delete(CourseModal model)
    {
        repository.delete(model);
    }

    public void deleteAllCourses()
    {
        repository.deleteAllCourses();
    }

    public LiveData<List<CourseModal>> getAllCourses()
    {
        return allCourses;
    }
}
