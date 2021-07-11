package com.sgranjon.kotlinexampleproject.data.component.trace

interface TraceComponent {
    fun traceVerbose(id: TraceId, vararg variables: Any?)

    fun traceDebug(id: TraceId, vararg variables: Any?)

    fun traceInfo(id: TraceId, vararg variables: Any?)

    fun traceWarning(id: TraceId, vararg variables: Any)

    fun traceError(id: TraceId, vararg variables: Any)

    fun traceCritical(id: TraceId, vararg variables: Any)
}