package me.nickland.inspirationalquote.util

import android.content.Context

/**
 * Created by HZF0PT on 10/27/2017.
 */
class AssetReaderUtil {
    companion object {
        fun asset(context: Context, assetPath: String) {
            val inputStream = context.assets.open("assets/${assetPath}")
            return inputStream.bufferedReader().use { it.readText() }
        }
    }
}