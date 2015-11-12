package com.beust.kobalt.misc

import com.beust.kobalt.maven.KobaltException
import com.google.inject.Singleton
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*

@Singleton
class LocalProperties {
    val localProperties: Properties by lazy {
        val result = Properties()
        val filePath = Paths.get("local.properties")
        if (! Files.exists(filePath)) {
            throw KobaltException("Couldn't find a local.properties file")
        }

        filePath.let { path ->
            if (Files.exists(path)) {
                Files.newInputStream(path).use {
                    result.load(it)
                }
            }
        }

        result
    }

    fun get(name: String) : String {
        val result = localProperties.get(name) ?: throw KobaltException("Couldn't find $name in local.properties")
        return result as String
    }
}
