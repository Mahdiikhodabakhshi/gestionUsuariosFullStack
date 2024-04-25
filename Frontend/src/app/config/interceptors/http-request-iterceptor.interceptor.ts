import {HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {catchError, Observable, retry, throwError} from "rxjs";

export class HttpRequestInterceptor implements HttpInterceptor {

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const retryNumber = 3;
    return next.handle(req)
      .pipe(
        retry(retryNumber),
        catchError((error:HttpErrorResponse)=>
          {
            let errorMessage ='';
            if (error.status){
              errorMessage = `Error Status ${error.status}\n Message: ${error.message}`
            }else {
              errorMessage = `Error ${error.message}`
            }
            console.log(errorMessage);
            return throwError(()=>error);
          }
        )
      );
  }

}
