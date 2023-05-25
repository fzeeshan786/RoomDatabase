package com.zeeshan.roomdatabase;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class CourseRepository
{
    // below line is the create a variable
    // for dao and list for all course.
    private Dao dao;
    private LiveData<List<CourseModal>> allCourses;

    // creating a constructor for our variable
    public CourseRepository(Application application)
    {
        CourseDatabase database = CourseDatabase.getInstance(application);
        dao = database.Dao();
        allCourses = dao.getAllCourse();

    }
    // creating a method to insert the data to our database
    public void insert(CourseModal model)
    {
        new InsertCourseAsyncTask(dao).execute(model);
    }


    public void update(CourseModal model)
    {
        new UpdateCourseAsyncTask(dao).execute(model);
    }

    public void delete(CourseModal model)
    {
        new DeleteCourseAsyncTask(dao).execute(model);
    }

    // below is the method to delete all the courses
    public void deleteAllCourses()
    {
        new DeleteAllCoursesAsyncTask(dao).execute();
    }

    //below method is to read all the courses
    public LiveData<List<CourseModal>> getAllCourses()
    {
        return allCourses;
    }

    // we are creating a async task method to insert new course
    private static class InsertCourseAsyncTask extends AsyncTask<CourseModal, Void, Void>
    {
        private Dao dao;

        private InsertCourseAsyncTask(Dao dao)
        {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(CourseModal... courseModals)
        {
            // below lone is use to insert our modal in dao
            dao.insert(courseModals[0]);
            return null;
        }
    }

    // we are creating a async task method to update our course
    private static class UpdateCourseAsyncTask extends AsyncTask<CourseModal, Void, Void>
    {
        private Dao dao;
        private UpdateCourseAsyncTask(Dao dao)
        {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(CourseModal... courseModals) {
            dao.update(courseModals[0]);
            return null;
        }
    }

    // we are creating a async task method to delete course
    private static class DeleteCourseAsyncTask extends AsyncTask<CourseModal, Void, Void>
    {
        private Dao dao;
        private DeleteCourseAsyncTask(Dao dao)
        {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(CourseModal... courseModals)
        {
            // below line is use to delete
            // our course modal in dao
            dao.delete(courseModals[0]);
            return null;
        }
    }

    // we are creating a async task method to delete all course
    private static class DeleteAllCoursesAsyncTask extends AsyncTask<Void, Void, Void>
    {
        private Dao dao;
        private DeleteAllCoursesAsyncTask(Dao dao)
        {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids)
        {
            // on below line calling method
            // to delete all course
            dao.deleteAllCourses();
            return null;
        }
    }
}