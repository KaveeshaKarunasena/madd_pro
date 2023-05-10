package com.example.madd_project

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.example.madd_project.R

public class HomeMainDirections private constructor() {
  public companion object {
    public fun actionHomeMainToHomeFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_homeMain_to_homeFragment)
  }
}
