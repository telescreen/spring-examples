package com.buiha.storage;

import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

/**
 * Created by tal on 2017/03/07.
 */
public interface StorageService {
    void store(MultipartFile file) throws StorageException;

    Stream<Path> loadAll() throws StorageException;

    Path load(String fileName);

    void deleteAll();

    void init() throws StorageException;
}
