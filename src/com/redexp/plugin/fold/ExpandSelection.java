package com.redexp.plugin.fold;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.FoldRegion;
import com.intellij.openapi.editor.FoldingModel;
import com.intellij.openapi.editor.SelectionModel;

public class ExpandSelection extends AnAction {
    public void actionPerformed(AnActionEvent e) {
        Editor editor = e.getData(PlatformDataKeys.EDITOR);
        if( editor == null ) {
            e.getPresentation().setEnabled(false);
            return;
        }

        final SelectionModel selection = editor.getSelectionModel();
        final FoldingModel folding = editor.getFoldingModel();
        folding.runBatchFoldingOperation(new Runnable() {
            @Override
            public void run() {
                for (FoldRegion region : folding.getAllFoldRegions()) {
                    if( region.getStartOffset() >= selection.getSelectionStart() && region.getEndOffset() <= selection.getSelectionEnd() ) {
                        region.setExpanded(true);
                    }
                }
            }
        });
    }
}
