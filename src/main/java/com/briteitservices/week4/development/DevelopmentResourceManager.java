package com.briteitservices.week4.development;

import io.undertow.server.handlers.resource.FileResourceManager;
import io.undertow.server.handlers.resource.Resource;
import io.undertow.server.handlers.resource.ResourceChangeListener;
import io.undertow.server.handlers.resource.ResourceManager;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Stuart Douglas
 */
public class DevelopmentResourceManager implements ResourceManager {

    private final List<FileResourceManager> fileResourceManagers = new ArrayList<>();
    private final ResourceManager delegate;

    public DevelopmentResourceManager(final String basePath, ResourceManager delegate) {
        this.delegate = delegate;
        Path path = Paths.get(basePath);

        try {
            Files.walkFileTree(path, new FileVisitor<Path>() {
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    if (!dir.toString().contains("target")) {
                        if (dir.getFileName().toString().equals("resources") && dir.getParent().getFileName().toString().equals("META-INF")) {
                            fileResourceManagers.add(new FileResourceManager(dir.toFile(), 1024));
                        } else if (dir.getFileName().toString().equals("webapp") && dir.getParent().getFileName().toString().equals("main")) {
                            fileResourceManagers.add(new FileResourceManager(dir.toFile(), 1024));
                        }
                    }
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            new RuntimeException(e);
        }
    }

    @Override
    public Resource getResource(String path) throws IOException {
        for (FileResourceManager rm : fileResourceManagers) {
            Resource res = rm.getResource(path);
            if (res != null) {
                return res;
            }
        }
        return delegate.getResource(path);
    }

    @Override
    public boolean isResourceChangeListenerSupported() {
        return false;
    }

    @Override
    public void registerResourceChangeListener(ResourceChangeListener listener) {
    }

    @Override
    public void removeResourceChangeListener(ResourceChangeListener listener) {
    }

    @Override
    public void close() throws IOException {
        delegate.close();
    }
}
