package top.vuhe.android.entity

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.databind.ser.std.StdSerializer

@JsonSerialize(using = ResponseSerializer::class)
enum class Response(
    val code: Int,
    val message: String
) {
    OK(200, "success"),
    ERROR(500, "error")
}

class ResponseSerializer : StdSerializer<Response> {
    constructor() : this(null)
    constructor(t: Class<Response>?) : super(t)

    override fun serialize(
        value: Response,
        gen: JsonGenerator,
        provider: SerializerProvider?
    ) {
        gen.writeStartObject()
        gen.writeNumberField("code", value.code)
        gen.writeStringField("message", value.message)
        gen.writeEndObject()
    }
}