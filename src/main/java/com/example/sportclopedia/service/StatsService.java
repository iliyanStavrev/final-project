package com.example.sportclopedia.service;

import com.example.sportclopedia.model.view.StatsView;

public interface StatsService {

    void onRequest();

    StatsView getStats();
}
