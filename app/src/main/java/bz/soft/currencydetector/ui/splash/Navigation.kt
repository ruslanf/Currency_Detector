package bz.soft.currencydetector.ui.splash

sealed class Navigation {
    object Authorized : Navigation()
    object NotAuthorized : Navigation()
}
