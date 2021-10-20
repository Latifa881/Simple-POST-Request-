package com.example.simplepostrequest

import com.google.gson.annotations.SerializedName

class Names {
    var name: List<Name>? = null

    class Name {
        @SerializedName("name")
        var name: String? = null

        constructor( name: String?) {

            this.name = name
        }
    }
}