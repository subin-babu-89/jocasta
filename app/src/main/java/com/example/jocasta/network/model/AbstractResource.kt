package com.example.jocasta.network.model

/**
 * Abstract call used to generalize the different resources types used in the app
 */
abstract class AbstractResource {
    abstract var url: String
    abstract var name: String?
    abstract var title: String?
}