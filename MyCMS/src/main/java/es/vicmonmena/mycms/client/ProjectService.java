package es.vicmonmena.mycms.client;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import es.vicmonmena.mycms.shared.ProjectDTO;

@RemoteServiceRelativePath("myCMSServices/projectService")
public interface ProjectService extends RemoteService {

	public void saveOrUpdate(ProjectDTO prj) throws Exception;
	
	public void delete(ProjectDTO prj) throws Exception;
	
	public ProjectDTO find(long id);
	
	public List<ProjectDTO> findAllEntries();
}
