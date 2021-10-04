I1=imread('fft1.tiff');
I2=imread('fft2.tiff');
I3=imread('fft3.tiff');
I4=imread('fft4.tiff');
[A1,A2,A3,A4]=olClean(double(I1),I2,I3,I4);
%subplot(2,2,1),imshow(A1);
%subplot(2,2,2),imshow(A2);
%subplot(2,2,3),imshow(A3);
%subplot(2,2,4),imshow(A4);



function [cImg1,cImg2,cImg3,cImg4] = olClean(img1,img2,img3,img4)
% 204663066 Shemesh Asaf
% 305373706 Seela Yishai
% Clean the four images using FFT. for each fft img clear the gray points
% in the middle.

% img1 clean
f = fft2(img1);
f_s = fftshift(f);
f_s(267,215) = 0;
f_s(267,227) = 0;
cImg1 = ifft2(ifftshift(f_s));
% img2 clean
f = fft2(img2);
f_s = fftshift(f);
A=fftshow(f_s,'log');
imshow(A);
f_s(232, 161) = 0;
f_s(250, 171) = 0;
cImg2 = ifft2(ifftshift(f_s));

% img3 clean
f = fft2(img3);
f_s = fftshift(f);
%fftShow(f_s);
f_s(180, 113) = 0;
f_s(180, 125) = 0;
f_s(180, 137) = 0;
f_s(190, 113) = 0;
f_s(190, 137) = 0;
f_s(200, 113) = 0;
f_s(200, 125) = 0;
f_s(200, 137) = 0;
cImg3 = ifft2(ifftshift(f_s));

% img4 clean
f = fft2(img4);
f_s = fftshift(f);
%fftShow(f_s);
f_s(293, 252) = 0;
f_s(300, 244) = 0;
f_s(300, 260) = 0;
f_s(307, 252) = 0;
cImg4 = ifft2(ifftshift(f_s));

end






function[answer]= fftshow(f,type)
% Usage: FFTSHOW(F,TYPE)
%
% Displays the fft matrix F using imshow, where TYPE must be one of
% 'abs' or 'log'. If TYPE='abs', then then abs(f) is displayed; if
% TYPE='log' then log(1+abs(f)) is displayed. If TYPE is omitted, then
% 'log' is chosen as a default.
%
% Example:
% c=imread('cameraman.tif');
% cf=fftshift(fft2(c));
% fftshow(cf,'abs')
%

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
