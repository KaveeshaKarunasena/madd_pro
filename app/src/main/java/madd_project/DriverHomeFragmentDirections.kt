package com.example.madd_project

import android.os.Bundle
import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import kotlin.Int
import kotlin.String

public class DriverHomeFragmentDirections private constructor() {
  private data class ActionDriverHomeFragmentToUpdateFragment(
    public val id: String,
    public val name: String,
    public val username: String,
    public val email: String,
    public val phone: String,
    public val vehiclenumber: String,
    public val password: String
  ) : NavDirections {
    public override val actionId: Int = R.id.action_driverHomeFragment_to_updateFragment

    public override val arguments: Bundle
      get() {
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
  }

  public companion object {
    public fun actionDriverHomeFragmentToAddDriverFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_driverHomeFragment_to_addDriverFragment)

    public fun actionDriverHomeFragmentToHomeFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_driverHomeFragment_to_homeFragment)

    public fun actionDriverHomeFragmentToUpdateFragment(
      id: String,
      name: String,
      username: String,
      email: String,
      phone: String,
      vehiclenumber: String,
      password: String
    ): NavDirections = ActionDriverHomeFragmentToUpdateFragment(id, name, username, email, phone,
        vehiclenumber, password)
  }
}
