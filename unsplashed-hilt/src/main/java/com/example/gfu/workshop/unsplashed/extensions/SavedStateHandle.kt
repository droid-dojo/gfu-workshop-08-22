import androidx.lifecycle.SavedStateHandle

inline fun <reified T> SavedStateHandle.require(
    key: String,
    errorMessage: String = "Key \"$key\" was not found in saved state"
): T = checkNotNull(get<T>(key)) { errorMessage }