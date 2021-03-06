package com.pjt.flowing.model.folder;

import com.pjt.flowing.model.Member;
import com.pjt.flowing.model.Timestamped;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class FolderTable extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="folderTable_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private Member member; // 생성한사람...

    @Column
    private String folderName;

    @Column
    private boolean trash;

    @Column
    private boolean bookmark;

    public FolderTable(String folderName, Member member) {
        this.folderName = folderName;
        this.member = member;   //여기서의 member는 폴더 생성자를 말한다.
        this.trash = false;
        this.bookmark = false;
    }
    public void setTrash(boolean trash) {
        this.trash = trash;
    }

    public void setBookmark(boolean bookmark) {
        this.bookmark = bookmark;
    }

}
