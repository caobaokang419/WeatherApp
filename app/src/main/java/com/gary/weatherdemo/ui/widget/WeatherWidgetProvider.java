package com.gary.weatherdemo.ui.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.gary.weatherdemo.R;

/**
 * Created by GaryCao on 2018/10/28.
 */
public class WeatherWidgetProvider extends AppWidgetProvider {
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
    }

    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
        updateWeatherWidget(context);
    }

    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
    }

    private void updateWeatherWidget(Context context){
        AppWidgetManager manager = AppWidgetManager.getInstance(context);
        RemoteViews remoteViews = new RemoteViews(
                context.getPackageName(), R.layout.widget_weather_main);
        remoteViews.setTextViewText(R.id.cur_weather, "for test");
        //manager.updateAppWidget(appWidgetId, remoteViews);
        manager.updateAppWidget(new ComponentName(context, WeatherWidgetProvider.class), remoteViews);
    }
}
