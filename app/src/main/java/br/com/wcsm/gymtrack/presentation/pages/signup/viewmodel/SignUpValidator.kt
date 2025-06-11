package br.com.wcsm.gymtrack.presentation.pages.signup.viewmodel

object SignUpValidator {
    private const val USERNAME_MIN_LENGTH = 3
    private const val USERNAME_MAX_LENGTH = 50
    private const val EMAIL_MAX_LENGTH = 100
    private const val PASSWORD_MIN_LENGTH = 5
    private const val PASSWORD_MAX_LENGTH = 20

    fun validateUserName(userName: String): Pair<Boolean, String> {
        return when {
            userName.isBlank() -> false to "Você deve informar seu nome."
            userName.length < USERNAME_MIN_LENGTH -> false to "Nome muito curto, mínimo de $USERNAME_MIN_LENGTH caracteres."
            userName.length > USERNAME_MAX_LENGTH -> false to "Nome muito grande (${userName.length}/$USERNAME_MAX_LENGTH)."
            else -> true to ""
        }
    }

    fun validateEmail(email: String): Pair<Boolean, String> {
        val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$".toRegex()

        return when {
            email.isBlank() -> false to "Você deve informar seu e-mail."
            email.contains(" ") -> false to "O e-mail não pode conter espaços."
            !email.matches(emailRegex) -> false to "Formato de e-mail inválido."
            email.length > EMAIL_MAX_LENGTH -> false to "E-mail muito longo (${email.length}/$EMAIL_MAX_LENGTH)."
            else -> true to ""
        }
    }

    fun validatePassword(password: String): Pair<Boolean, String> {
        return when {
            password.isBlank() -> false to "Você deve informar uma senha."
            password.contains(" ") -> false to "A senha não pode conter espaços."
            password.length < PASSWORD_MIN_LENGTH -> false to "Senha muito curta, mínimo de $PASSWORD_MIN_LENGTH caracteres."
            password.length > PASSWORD_MAX_LENGTH -> false to "Senha muito grande (${password.length}/$PASSWORD_MAX_LENGTH)."
            else -> true to ""
        }
    }

    fun validateConfirmPassword(
        password: String,
        confirmPassword: String
    ): Pair<Boolean, String> {
        return when {
            confirmPassword.isBlank() -> false to "Você deve confirmar sua senha."
            password != confirmPassword -> false to "As senhas não são iguais."
            else -> true to ""
        }
    }
}