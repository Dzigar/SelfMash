package com.selfmash.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.selfmash.beans.PostBean;
import com.selfmash.dao.PhotoDAO;
import com.selfmash.model.Photo;
import com.selfmash.model.User;
import com.selfmash.service.EstimationService;
import com.selfmash.service.PhotoService;
import com.selfmash.service.PostService;
import com.selfmash.service.UserService;

/**
 * 
 * @author Dzigar
 * 
 */
@Service("photoServiceImpl")
@Transactional
public class PhotoServiceImpl implements PhotoService {

    /**
     * 
     */
    @Autowired
    private PhotoDAO photoDAO;

    @Autowired
    private UserService userService;

    @Autowired
    private PostBean postBean;

    @Autowired
    private PostService postService;

    @Autowired
    private EstimationService estimationService;

    /**
     * Logger for PhotoServiceImpl class.
     */
    private Logger logger = Logger.getLogger(getClass().getName());

    /**
     * @param photo
     *            - object Photo for saving
     */
    @Override
    public void addPhoto(Photo photo) {
        photoDAO.addphoto(photo);
        // Create post with uploaded photo.
        postBean.addPost(photo.getUser().getId(), photo);
    }

    /**
     * @param user
     *            - object User
     * @return list of photo User
     */
    @Override
    public final List<Photo> getUserPhotos(final User user) {
        return photoDAO.getUserPhotos(user);
    }

    /**
     * @param id
     *            - user id
     * @return list of photo by user id
     */
    @Override
    public final Photo getPhotoById(final long id) {
        return photoDAO.getPhotoById(id);
    }

    /**
     * @param photo
     *            - object Photo for update
     */
    @Override
    public final void updatePhoto(final Photo photo) {
        photoDAO.updatePhoto(photo);
    }

    /**
     * Delete Photo by id.
     * 
     * @param id
     *            - Photo id
     * 
     */
    @Override
    public void deletePhoto(Photo photo) {
        try {
            if (photo.getUser().getProfilePhoto().equals(photo)) {
                userService.removeProfilePhoto(photo.getUser().getId());
            }
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
        } finally {
            try {
                postService.deletePost(photo.getPost());

                estimationService.removeEstimationsByPhotoId(photo.getId());
                photoDAO.deletePhoto(photo);
            } catch (Exception e2) {
                logger.error(e2.getLocalizedMessage());
            }
        }
    }
}
