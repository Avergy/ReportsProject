package beans;

import views.PdfView;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

@ManagedBean
public class ReportBean {

    private Date fromDate;
    private Date toDate;

    @EJB
    private PdfView pdfView;

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public void getOrderPerPeriod() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext ec = context.getExternalContext();
        HttpServletResponse response = (HttpServletResponse) ec.getResponse();
        ByteArrayOutputStream stream = pdfView.getPdfReportOrders(fromDate, toDate);
        if(stream != null){
            OutputStream outputStream = response.getOutputStream();
            stream.writeTo(outputStream);
            outputStream.flush();
        }

        response.setContentType("application/pdf");
        context.responseComplete();
    }


}
