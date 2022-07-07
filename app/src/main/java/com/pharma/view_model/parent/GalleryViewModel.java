package com.pharma.view_model.parent;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.pharma.model.parent.EventsListModel;
import com.pharma.model.parent.GalleryListModel;
import com.pharma.repository.student.EventsRepository;
import com.pharma.repository.student.GalleryRepository;

public class GalleryViewModel extends AndroidViewModel {
    GalleryRepository GalleryRep;
    private LiveData<GalleryListModel> galleryResponseLiveData;
    public GalleryViewModel(@NonNull Application application) {
        super(application);
        GalleryRep = new GalleryRepository(application);
        this.galleryResponseLiveData = GalleryRep.getGallery();
    }
    public LiveData<GalleryListModel> getGalleryResponseLiveData() {
        return galleryResponseLiveData;
    }
}
