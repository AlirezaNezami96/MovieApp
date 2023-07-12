package alireza.nezami.common.utils

import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flattenMerge

@OptIn(FlowPreview::class)
fun <T> flattenMerge(vararg flows: Flow<T>): Flow<T> {
    return flows.asFlow().flattenMerge()
}
