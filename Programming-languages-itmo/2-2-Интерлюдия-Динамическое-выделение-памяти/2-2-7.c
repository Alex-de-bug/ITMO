void marray_free( int64_t** marray, size_t rows ) {
     for( size_t i = 0; i < rows; i = i + 1 ) {
        if(marray[i]){
            free(marray[i]);
        }
      }
    free(marray);
}
