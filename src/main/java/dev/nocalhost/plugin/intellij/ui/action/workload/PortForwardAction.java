package dev.nocalhost.plugin.intellij.ui.action.workload;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.DumbAwareAction;
import com.intellij.openapi.project.Project;

import org.jetbrains.annotations.NotNull;

import dev.nocalhost.plugin.intellij.ui.dialog.PortForwardConfigurationDialog;
import dev.nocalhost.plugin.intellij.ui.tree.node.ResourceNode;

public class PortForwardAction extends DumbAwareAction {

    private final Project project;
    private final ResourceNode node;

    public PortForwardAction(Project project, ResourceNode node) {
        super("Port Forward");
        this.project = project;
        this.node = node;
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        new PortForwardConfigurationDialog(node, project).show();
    }
}
