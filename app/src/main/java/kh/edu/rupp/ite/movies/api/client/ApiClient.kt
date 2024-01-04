package kh.edu.rupp.ite.movies.api.client

class ApiClient private constructor(){

    companion object{
        private var instance: ApiClient? = null
        fun get(): ApiClient{
            if(instance == null){
                instance = ApiClient()
            }
            return instance!!
        }
    }
}