package com.pharma.retrofit;

import com.google.gson.JsonObject;
import com.pharma.model.parent.AttendanceListModel;
import com.pharma.model.parent.ElearningNewModel;
import com.pharma.model.parent.EventsListModel;
import com.pharma.model.parent.ExamListModel;
import com.pharma.model.parent.GalleryListModel;
import com.pharma.model.parent.HomeScreenDataListModel;
import com.pharma.model.parent.HomeWorkListModel;
import com.pharma.model.parent.NotificationListModel;
import com.pharma.model.parent.NotificationUpdateModel;
import com.pharma.model.parent.OnlineClassesListModel;
import com.pharma.model.parent.PaymentHistoryModel;
import com.pharma.model.parent.PaymentsListModel;
import com.pharma.model.parent.TimeTableListModel;
import com.pharma.model.parent.TransactionStatusModel;
import com.pharma.model.parent.TransactionsListModel;
import com.pharma.response.LoginResponse;
import com.pharma.response.parent.ArticleResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiRequest {
    @GET("v2/everything/")
    Call<ArticleResponse> getMovieArticles(
            @Query("q") String query,
            @Query("apikey") String apiKey
    );
    @Headers("Content-Type: application/json")
    @POST("login")
    Call<LoginResponse> login(@Body JsonObject body);

    @Headers("Content-Type: application/json")
    @POST("payThroughAtom")
    Call<TransactionStatusModel> updateTransStatus(@Body JsonObject body);

    @GET("eventsDet")
    Call<EventsListModel> getEvents();

    @GET("notify")
    Call<NotificationListModel> getNotifications();

    @GET("onlinecls")
    Call<OnlineClassesListModel> getOnlineClasses();

    @GET("exam")
    Call<ExamListModel> getExams();

    @GET("stuhomework")
    Call<HomeWorkListModel> getHomeWorks();

    @GET("stutimetable")
    Call<TimeTableListModel> getTimeTable();

    @GET("paymentDetails")
    Call<PaymentsListModel> getPayments();

    @GET("getGallery")
    Call<GalleryListModel> getGallery();

    @GET("atomPayTransactions")
    Call<TransactionsListModel> getPayTransactions();


    @Headers("Content-Type: application/json")
    @POST("stuAttDetails")
    Call<AttendanceListModel> getAttendance(@Body JsonObject body);

    @GET("homescreendet")
    Call<HomeScreenDataListModel> getHomeScreenDetails();

    @GET("elearningLinks")
    Call<ElearningNewModel> getElearningLinks();

    @GET("stupayments")
    Call<PaymentHistoryModel> getStudentPayments();

    @POST("updateNoticesCnt")
    Call<NotificationUpdateModel> updateNoticesCnt();
}
