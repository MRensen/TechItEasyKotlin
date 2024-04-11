import jakarta.validation.constraints.NotNull

data class LoginRequestDto(@NotNull val username: String, @NotNull val password: String)