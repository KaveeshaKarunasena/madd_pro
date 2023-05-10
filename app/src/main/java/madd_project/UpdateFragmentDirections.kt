package com.example.madd_project

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.example.madd_project.R

public class UpdateFragmentDirections private constructor() {
  public companion object {
    public fun actionUpdateFragmentToDriverHomeFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_updateFragment_to_driverHomeFragment)
  }
}
