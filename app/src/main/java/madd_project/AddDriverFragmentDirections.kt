package com.example.madd_project

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.example.madd_project.R

public class AddDriverFragmentDirections private constructor() {
  public companion object {
    public fun actionAddDriverFragmentToDriverHomeFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_addDriverFragment_to_driverHomeFragment)

    public fun actionAddDriverFragmentToHomeFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_addDriverFragment_to_homeFragment)
  }
}
