package moteur_police;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Paths;
import java.util.Collection;

import javax.ejb.EJB;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class Servlet
 */
@WebServlet("/Servlet")
@MultipartConfig
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private Facade f = new Facade();
	
	String chemin_eigenfaces = System.getProperty("jboss.server.data.dir") + "/eigenfaces";
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response); // response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String op = request.getParameter("op");
		switch(op) {
		case "valider_ajout" :
			String nom = request.getParameter("nom");
			String prenom = request.getParameter("prenom");
			int numero = Integer.parseInt(request.getParameter("numero"));
			f.ajouterPersonne(nom, prenom, numero);
			//*************************************
			
			Part filePart = request.getPart("formFile");
		    String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
		    String extension = fileName.substring(fileName.lastIndexOf(".")+1);

		    // Il faut gérer les homonymes
		    String id_image = nom + prenom + "." + extension;

		    // Sauvegarde du flux dans un fichier
		    sauvegarde_image((int) filePart.getSize(), filePart.getInputStream(), chemin_eigenfaces + "/" + id_image);
		    
		    //**************************************
			request.getRequestDispatcher("vue.jsp").forward(request, response);
			break;
		case "render_image" :
			render_image(chemin_eigenfaces + "/" + request.getParameter("nom") + request.getParameter("prenom") + ".jpg", request, response);
			break;
		case "valider_recherche" :
			int numero_suspect = Integer.parseInt(request.getParameter("numero"));
			Identite suspect = f.retourSuspect(numero_suspect);
			request.setAttribute("suspect", suspect);
			request.getRequestDispatcher("suspect.jsp").forward(request, response);
			break;
		case "dossier_affaire" :
			request.getRequestDispatcher("affaire.jsp").forward(request, response);
			break;
			
        case "login" :
            // il faut rajouter un parameter "op" quand on clique sur le bouton login qui passe à login
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            // ALLER CHERCHE LE MDP DANS LA BD
            // Faire une fonction dans la façade qui permet de récuperer le mdp dans la bd avec le username
            // Dans la DB faire une nouvelle table avec le username en clé primaire et les mdp et email en attributs
            // username = clé primaire de la entity User (pour le login)
            if (f.isUserPresent(username)) {
                String passwordBD = f.getUserPassword(username);    // on cherche le MDP dans la BD
                if (password == passwordBD) {
                    request.getRequestDispatcher("accueil.html").forward(request, response);
                } else {
                    request.getRequestDispatcher("index.html").forward(request, response);
                }
            } else {
                request.getRequestDispatcher("index.html").forward(request, response);
            }        
            break;
        case "signup" :
            String username_enregistrement = request.getParameter("username");
            String email = request.getParameter("email");
            String password_enregistrement = request.getParameter("password");    
            // Faire une fonction dans la Façade permettant de rajouter des gens dans la BD avec des persist
            // Fair des fonctions dans la façade qui gèrent si on essaye d'enregistre la même personne ou jsp quoi
            if (!f.isUserPresent(username_enregistrement)) {
                f.addUser(username_enregistrement, email, password_enregistrement);
                System.out.println("***********************************************");
                System.out.println(email);
                System.out.println(username_enregistrement);
                System.out.println(password_enregistrement);
                System.out.println("***********************************************");
                request.getRequestDispatcher("index.html").forward(request, response);        // on retourne au login après    
            } else {
                request.getRequestDispatcher("signup.html").forward(request, response);     // on pourrait essayer de rajouter des 
            }                                                                             // messages d'erreur mais flemme
            break;
            
		case "retour_accueil" :
			request.getRequestDispatcher("accueil.html").forward(request, response);
			break;
		default :
			//response.getWriter().print("<html><body><p>Opération default<p><body><html>");
			request.getRequestDispatcher("accueil.html").forward(request, response);
			break;
		}
	}
	
	public static void sauvegarde_image(int inSize,InputStream inSt,String outFileName) throws IOException{
	    // Read 1 Mo byte chunks
	    FileOutputStream nFileOut = new FileOutputStream(outFileName);

	    byte bytFrag[]=new byte[1000000];
	    long totalRead =0;
	    int bRead=0;
	    while(totalRead<inSize && bRead!=-1) {
	        bRead=inSt.read(bytFrag);
	        if(bRead>0)
	            nFileOut.write(bytFrag,0,bRead);
	        totalRead+=bRead;
	    }
	    nFileOut.close();
	}
	
	public static void render_image(String imagePath, HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletContext cntx=request.getServletContext();
        String mime = cntx.getMimeType(imagePath);
        if(mime==null) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }
        response.setContentType(mime);

        File imgFile = new File(imagePath);
        response.setContentLength((int)imgFile.length());

        FileInputStream fInSt = new FileInputStream(imagePath);
        OutputStream outSt = response.getOutputStream();

        byte bytFrag[]=new byte[1000000];
        long totalRead=0;
        int bRead=0;
        while(totalRead<imgFile.length() && bRead!=-1) {
            bRead=fInSt.read(bytFrag);
            if(bRead>0)
                outSt.write(bytFrag,0,bRead);
            totalRead+=bRead;
        }
        outSt.close();
        fInSt.close();
    }
    
}



