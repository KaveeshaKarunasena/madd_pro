package com.example.madd_project

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.example.madd_project.R

public class HomeFragmentDirections private constructor() {
  public companion object {
    public fun actionHomeFragmentToDriverHomeFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_adminhomeFragment_to_driverHomeFragment)

    public fun actionHomeFragmentToHomeMain(): NavDirections =
        ActionOnlyNavDirections(R.id.action_homeFragment_to_homeMain)
  }
}
