package tn.esprit.springfever.Services.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import tn.esprit.springfever.Repositories.FileSystemRepository;
import tn.esprit.springfever.Repositories.ImageRepository;
import tn.esprit.springfever.Repositories.ProjectRepository;
import tn.esprit.springfever.Repositories.VideoRepository;
import tn.esprit.springfever.Services.Interface.IFileLocationService;
import tn.esprit.springfever.entities.Image;
import tn.esprit.springfever.entities.User;
import tn.esprit.springfever.entities.Video;

@Service
public class FileLocationService implements IFileLocationService {


    @Autowired
    FileSystemRepository fileSystemRepository;
    @Autowired
    ImageRepository imageDataRepository;


    @Autowired
    VideoRepository videoRepository;
    @Autowired
    ProjectRepository projectRepository;

    public Image save(MultipartFile file) throws Exception {
        String location = fileSystemRepository.save(file);
        return imageDataRepository.save(new Image(file.getOriginalFilename(), location));
    }
    public FileSystemResource find(Long imageId) {
        Image image = imageDataRepository.findById(imageId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return fileSystemRepository.findInFileSystem(image.getLocation());
    }
    public Video saveVideo(byte[] bytes, String videoName)throws Exception{
        String location = fileSystemRepository.saveVideo(bytes, videoName);
        return videoRepository.save(new Video(videoName, location));
    }

    public FileSystemResource findVideo(Long idVideo){
        Video video = videoRepository.findById(idVideo).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return fileSystemRepository.findInFileSystem(video.getLocation());
    }

}
