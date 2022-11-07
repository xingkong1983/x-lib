package com.xgitlink.lib.git.mo;

import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.treewalk.TreeWalk;

import com.xgitlink.lib.core.plugin.IPrint;

import lombok.Data;

@Data
public class RepoFileMo implements IPrint {
	
	private String path;
	private String name;
	private Boolean isDir;
	private RepoCommitMo commitMo;

	public RepoFileMo(TreeWalk treeWalk, RevCommit commit) {
		this.name = treeWalk.getNameString();
		this.path = treeWalk.getPathString();
		this.isDir = treeWalk.isSubtree() ? true : false;
		
		
		
		//		walk = new RevWalk(repository);
		//		RevCommit commit = walk.parseCommit(head.getObjectId());
		
		
		// ObjectId objectId = treeWalk.getObjectId(0);
		// ObjectLoader loader = repository.open(objectId);
		// loader.copyTo(System.out); // 提取blob对象的内容
	}
}
