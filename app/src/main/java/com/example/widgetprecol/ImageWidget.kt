package com.example.widgetprecol

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.widget.RemoteViews
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import kotlinx.coroutines.*

/**
 * Implementation of App Widget functionality.
 */
class ImageWidget : AppWidgetProvider() {
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        for (i in appWidgetIds.indices) {
            val rv = RemoteViews(context.packageName, R.layout.image_widget)
            CoroutineScope(Dispatchers.Main).launch {
                rv.setImageViewBitmap(R.id.appwidget_image, getBitmap(context))
            }
            appWidgetManager.updateAppWidget(appWidgetIds[i], rv)

        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

internal fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int
) {

    // Construct the RemoteViews object

}

private suspend fun getBitmap(context: Context): Bitmap {
    val loader = ImageLoader(context)
    val req = ImageRequest.Builder(context)
        .apply {
            data("https://images.dog.ceo/breeds/saluki/n02091831_3400.jpg")
            allowHardware(false)
        }.build()
    val result = (loader.execute(req) as SuccessResult).drawable
    return (result as BitmapDrawable).bitmap
}

