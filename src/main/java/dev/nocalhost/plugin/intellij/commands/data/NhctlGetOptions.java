package dev.nocalhost.plugin.intellij.commands.data;

import java.nio.file.Path;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NhctlGetOptions extends NhctlGlobalOptions {
    private String application;

    public NhctlGetOptions(Path kubeConfigPath, String namespace) {
        super(kubeConfigPath, namespace);
    }
}
