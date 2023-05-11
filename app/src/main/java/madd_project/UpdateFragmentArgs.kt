package com.example.madd_project

import android.os.Bundle
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavArgs
import java.lang.IllegalArgumentException
import kotlin.String
import kotlin.jvm.JvmStatic

public data class UpdateFragmentArgs(
  public val id: String,
  public val name: String,
  public val username: String,
  public val email: String,
  public val phone: String,
  public val vehiclenumber: String,
  public val password: String
) : NavArgs {
  public fun toBundle(): Bundle {
    val result = Bundle()
    result.putString("id", this.id)
    result.putString("name", this.name)
    result.putString("username", this.username)
    result.putString("email", this.email)
    result.putString("phone", this.phone)
    result.putString("vehiclenumber", this.vehiclenumber)
    result.putString("password", this.password)
    return result
  }

  public fun toSavedStateHandle(): SavedStateHandle {
    val result = SavedStateHandle()
    result.set("id", this.id)
    result.set("name", this.name)
    result.set("username", this.username)
    result.set("email", this.email)
    result.set("phone", this.phone)
    result.set("vehiclenumber", this.vehiclenumber)
    result.set("password", this.password)
    return result
  }

  public companion object {
    @JvmStatic
    public fun fromBundle(bundle: Bundle): UpdateFragmentArgs {
      bundle.setClassLoader(UpdateFragmentArgs::class.java.classLoader)
      val __id : String?
      if (bundle.containsKey("id")) {
        __id = bundle.getString("id")
        if (__id == null) {
          throw IllegalArgumentException("Argument \"id\" is marked as non-null but was passed a null value.")
        }
      } else {
        throw IllegalArgumentException("Required argument \"id\" is missing and does not have an android:defaultValue")
      }
      val __name : String?
      if (bundle.containsKey("name")) {
        __name = bundle.getString("name")
        if (__name == null) {
          throw IllegalArgumentException("Argument \"name\" is marked as non-null but was passed a null value.")
        }
      } else {
        throw IllegalArgumentException("Required argument \"name\" is missing and does not have an android:defaultValue")
      }
      val __username : String?
      if (bundle.containsKey("username")) {
        __username = bundle.getString("username")
        if (__username == null) {
          throw IllegalArgumentException("Argument \"username\" is marked as non-null but was passed a null value.")
        }
      } else {
        throw IllegalArgumentException("Required argument \"username\" is missing and does not have an android:defaultValue")
      }
      val __email : String?
      if (bundle.containsKey("email")) {
        __email = bundle.getString("email")
        if (__email == null) {
          throw IllegalArgumentException("Argument \"email\" is marked as non-null but was passed a null value.")
        }
      } else {
        throw IllegalArgumentException("Required argument \"email\" is missing and does not have an android:defaultValue")
      }
      val __phone : String?
      if (bundle.containsKey("phone")) {
        __phone = bundle.getString("phone")
        if (__phone == null) {
          throw IllegalArgumentException("Argument \"phone\" is marked as non-null but was passed a null value.")
        }
      } else {
        throw IllegalArgumentException("Required argument \"phone\" is missing and does not have an android:defaultValue")
      }
      val __vehiclenumber : String?
      if (bundle.containsKey("vehiclenumber")) {
        __vehiclenumber = bundle.getString("vehiclenumber")
        if (__vehiclenumber == null) {
          throw IllegalArgumentException("Argument \"vehiclenumber\" is marked as non-null but was passed a null value.")
        }
      } else {
        throw IllegalArgumentException("Required argument \"vehiclenumber\" is missing and does not have an android:defaultValue")
      }
      val __password : String?
      if (bundle.containsKey("password")) {
        __password = bundle.getString("password")
        if (__password == null) {
          throw IllegalArgumentException("Argument \"password\" is marked as non-null but was passed a null value.")
        }
      } else {
        throw IllegalArgumentException("Required argument \"password\" is missing and does not have an android:defaultValue")
      }
      return UpdateFragmentArgs(__id, __name, __username, __email, __phone, __vehiclenumber,
          __password)
    }

    @JvmStatic
    public fun fromSavedStateHandle(savedStateHandle: SavedStateHandle): UpdateFragmentArgs {
      val __id : String?
      if (savedStateHandle.contains("id")) {
        __id = savedStateHandle["id"]
        if (__id == null) {
          throw IllegalArgumentException("Argument \"id\" is marked as non-null but was passed a null value")
        }
      } else {
        throw IllegalArgumentException("Required argument \"id\" is missing and does not have an android:defaultValue")
      }
      val __name : String?
      if (savedStateHandle.contains("name")) {
        __name = savedStateHandle["name"]
        if (__name == null) {
          throw IllegalArgumentException("Argument \"name\" is marked as non-null but was passed a null value")
        }
      } else {
        throw IllegalArgumentException("Required argument \"name\" is missing and does not have an android:defaultValue")
      }
      val __username : String?
      if (savedStateHandle.contains("username")) {
        __username = savedStateHandle["username"]
        if (__username == null) {
          throw IllegalArgumentException("Argument \"username\" is marked as non-null but was passed a null value")
        }
      } else {
        throw IllegalArgumentException("Required argument \"username\" is missing and does not have an android:defaultValue")
      }
      val __email : String?
      if (savedStateHandle.contains("email")) {
        __email = savedStateHandle["email"]
        if (__email == null) {
          throw IllegalArgumentException("Argument \"email\" is marked as non-null but was passed a null value")
        }
      } else {
        throw IllegalArgumentException("Required argument \"email\" is missing and does not have an android:defaultValue")
      }
      val __phone : String?
      if (savedStateHandle.contains("phone")) {
        __phone = savedStateHandle["phone"]
        if (__phone == null) {
          throw IllegalArgumentException("Argument \"phone\" is marked as non-null but was passed a null value")
        }
      } else {
        throw IllegalArgumentException("Required argument \"phone\" is missing and does not have an android:defaultValue")
      }
      val __vehiclenumber : String?
      if (savedStateHandle.contains("vehiclenumber")) {
        __vehiclenumber = savedStateHandle["vehiclenumber"]
        if (__vehiclenumber == null) {
          throw IllegalArgumentException("Argument \"vehiclenumber\" is marked as non-null but was passed a null value")
        }
      } else {
        throw IllegalArgumentException("Required argument \"vehiclenumber\" is missing and does not have an android:defaultValue")
      }
      val __password : String?
      if (savedStateHandle.contains("password")) {
        __password = savedStateHandle["password"]
        if (__password == null) {
          throw IllegalArgumentException("Argument \"password\" is marked as non-null but was passed a null value")
        }
      } else {
        throw IllegalArgumentException("Required argument \"password\" is missing and does not have an android:defaultValue")
      }
      return UpdateFragmentArgs(__id, __name, __username, __email, __phone, __vehiclenumber,
          __password)
    }
  }
}
