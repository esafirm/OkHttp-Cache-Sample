package nolambda.cachesample.di.helpers

interface HasComponent<T> {
    fun getComponent(): T
}
