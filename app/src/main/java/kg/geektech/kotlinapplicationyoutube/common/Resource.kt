package kg.geektech.kotlinapplicationyoutube.common


class Resource<T>(val data: T, val msg: String?, val status: Status) {
    enum class Status {
        SUCCESS, ERROR, LOADING
    }

    companion object {
        fun <T> success(data: T): Resource<T> {
            return Resource(data, null, Status.SUCCESS)
        }

        fun <T> error(msg: String?, data: T?): Resource<T?> {
            return Resource(data, msg, Status.ERROR)
        }

        fun <T> loading(): Resource<T?> {
            return Resource(null, null, Status.LOADING)
        }
    }
}