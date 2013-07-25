package es.vicmonmena.mycms.client;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import es.vicmonmena.mycms.shared.ProjectDTO;
import es.vicmonmena.mycms.shared.ResourceDTO;

@RemoteServiceRelativePath("myCMSServices/resourceService")
public interface ResourceService {

	public void saveOrUpdate(ResourceDTO rsc) throws Exception;
	
	public void delete(ResourceDTO rsc) throws Exception;
	
	public ProjectDTO find(long id);
	
	public List<ResourceDTO> findAllEntries();
}
