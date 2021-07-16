package com.sgranjon.kotlinexampleproject.data.component.trace

enum class TraceId {
    /**
     * DB Mappers
     */

    DB_MAPPER_CHARACTER,

    /**
     * Remote Mappers
     */

    REMOTE_MAPPER_CHARACTER,
    REMOTE_MAPPER_EPISODE,
    REMOTE_MAPPER_LOCATION,

    /**
     * Model Mappers
     */

    MODEL_MAPPER_CHARACTER,
    MODEL_MAPPER_EPISODE,
    MODEL_MAPPER_LOCATION

}