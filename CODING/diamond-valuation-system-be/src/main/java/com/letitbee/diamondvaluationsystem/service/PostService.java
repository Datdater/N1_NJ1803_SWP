package com.letitbee.diamondvaluationsystem.service;

import com.letitbee.diamondvaluationsystem.payload.PostDTO;
import com.letitbee.diamondvaluationsystem.payload.Response;

public interface PostService {

    PostDTO createPost(PostDTO postDto);

    Response<PostDTO> getAllPost(int pageNo, int pageSize, String sortBy, String sortDir);

    PostDTO getPostById(long id);

    PostDTO updatePost(PostDTO postDto, long id);

    void deletePostById(long id);
}
