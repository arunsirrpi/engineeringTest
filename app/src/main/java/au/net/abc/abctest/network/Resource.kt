package au.net.abc.abctest.network

class Resource {

    fun getResponse() =
        this::class.java.getResource("/ApiResponse.json")?.readText() ?: "{}"

}