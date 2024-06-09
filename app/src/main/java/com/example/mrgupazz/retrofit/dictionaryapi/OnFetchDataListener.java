package com.example.mrgupazz.retrofit.dictionaryapi;

import com.example.mrgupazz.api.dictionaryapi.APIResponse;

public interface OnFetchDataListener {
    void onFetchData(APIResponse apiResponse, String message);
    void onError(String message);
}
