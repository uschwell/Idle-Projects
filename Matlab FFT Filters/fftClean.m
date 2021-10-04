%327349031 Uriel Schwell
%316213894 Adi Tsolshain
%Ex5
I=imread('fft3.tiff');
subplot(1,2,1),imshow(I);
I2=fftlean(I);
subplot(1,2,2),imshow(I2);

%{
this function receives several (unshifted) fft images, it then 'zeroes out' two (or more) pixels
 to remove the relevant interference wave (as calculated with the included
"fftshow" function). It then converts the altered image back into a
greyscale picture.
%}
%function [cImg1,cImg2,cImg3,cImg4] = fftClean(input1,input2,input3,input4)
function [cImg3] = fftlean(input3)
%first image
%{
%we convert our input to its fft form
temp=fft2(input1);
%we shift our DC to the center
shifted=fftshift(temp);
%remove two pixels
shifted(253,213)=0;
shifted(281,229)=0;
%we return our DC to the top left for easier ifft (inverse fft)
temp = ifftshift(shifted);
%we convert back from the fft to the 'regular' format
stepBack=ifft2(temp);
%we return the (filtered) picture back to its original form
%after converting the doubles back to integers (for ease-of print/output)
cImg1=uint8(stepBack);


%2nd image

%we convert our input to its fft form
temp=fft2(input2);
%we shift our DC to the center
shifted=fftshift(temp);
%remove two pixels
shifted(231,175)=0;
shifted(251,157)=0;
%we return our DC to the top left for easier ifft (inverse fft)
temp = ifftshift(shifted);
%we convert back from the fft to the 'regular' format
stepBack=ifft2(temp);
%we return the (filtered) picture back to its original form
%after converting the doubles back to integers (for ease-of print/output)
cImg2=uint8(stepBack);

%}
%3rd image
%we convert our input to its fft form
temp=fft2(input3);
%we shift our DC to the center
shifted=fftshift(temp);
%remove 8 pixels
shifted(174,112)=0;
shifted(174,125)=0;
shifted(174,138)=0;
shifted(190,112)=0;
shifted(190,138)=0;
shifted(206,112)=0;
shifted(206,125)=0;
shifted(206,138)=0;
%we return our DC to the top left for easier ifft (inverse fft)
temp = ifftshift(shifted);
%we convert back from the fft to the 'regular' format
stepBack=ifft2(temp);
%we return the (filtered) picture back to its original form
%after converting the doubles back to integers (for ease-of print/output)
cImg3=uint8(stepBack);






%{
%4th image


%we convert our input to its fft form
temp=fft2(input4);
%we shift our DC to the center
shifted=fftshift(temp);

%remove 4 pixels
shifted(300,234)=0;
shifted(311,252)=0;
shifted(289,252)=0;
shifted(300,270)=0;

%we return our DC to the top left for easier ifft (inverse fft)
temp = ifftshift(shifted);
%we convert back from the fft to the 'regular' format
stepBack=ifft2(temp);
%we return the (filtered) picture back to its original form
%after converting the doubles back to integers (for ease-of print/output)
cImg4=uint8(stepBack);
%}
end



%{
this function receives a DC-centric (i.e-fftshifted) fft matrix, it then
returns the fft image as a greyscale image (that imshow() is capable of
displaying), in either an abs() or a log() representaion scale.
%}
function[answer]= fftshow(f,type)

if nargin<2
type='log';
end
if (strcmp(type,'log'))
fl = log(1+abs(f));
fm = max(fl(:));
answer=(im2uint8(fl/fm));
elseif (strcmp(type,'abs'))
fa=abs(f);
fm=max(fa(:));
answer=(fa/fm);
else
error('TYPE must be abs or log.');
end;
end
