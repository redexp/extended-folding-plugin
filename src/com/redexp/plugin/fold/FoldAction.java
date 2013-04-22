package com.redexp.plugin.fold;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.FoldRegion;
import com.intellij.openapi.editor.FoldingModel;

//https://github.com/JetBrains/intellij-community/blob/0e4bd4695776cd4577ce8e43d68f3d8f8b661e29/platform/platform-tests/testSrc/com/intellij/openapi/editor/FoldingTest.java
//http://blogs.jetbrains.com/idea/2012/12/webinar-recording-live-coding-a-plugin-from-scratch/
//http://confluence.jetbrains.com/display/IDEADEV/PluginDevelopment

public class FoldAction extends AnAction {
    public void actionPerformed(AnActionEvent e) {
        Editor editor = e.getData(PlatformDataKeys.EDITOR);
        if( editor == null ) {
            e.getPresentation().setEnabled(false);
            return;
        }

        final FoldingModel folding = editor.getFoldingModel();
        folding.runBatchFoldingOperation(new Runnable() {
            @Override
            public void run() {
                for (FoldRegion region : folding.getAllFoldRegions()) {
                    String str = region.getDocument().getText(new TextRange(region.getStartOffset(), region.getStartOffset() + 8));
                    if (str.equals("//region")) {
                        region.setExpanded(false);
                    }
                }
            }
        });
    }
}
