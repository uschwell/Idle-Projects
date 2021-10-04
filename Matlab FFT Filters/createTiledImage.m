%327349031 Uriel Schwell
%316213894 Adi Tsolshain
%Targil Bonus


%{
Input:bigImage,smallImage
Function Operation: This function receives 2 separate images, and reproduces the "bigImage" by
replacing it's pixels with the "smallImage".
Process:It does this by cutting the "bigImage" into squares the precise size of the
"smallImage", we then create a new 'pixel' by altering the "smallImage"
until its histogram matches the histogram of the newly defined 'pixel'.
Then the new 'histogram-smallImage' is used to replace the pixel.

%}
function[nImg] = createTiledImage(bigImage, smallImage)
%we determine the size of the large image
[row,col]=size(bigImage);
%we determine the size of each new 'pixel'
[pRow,pCol]=size(smallImage);
%we declare our final image
buffer=zeros(size(bigImage));

%outer two loops move by increments of pixel size
for m=1:pRow:row
   for n=1:pCol:col
       newPixel=zeros(pRow,pCol);
%inner two loops work on each individual pixel        
%for each new 'pixel' we copy the 'old' pixels to a temporary matrix
    for i=1:(pRow-1)
     for j=1:(pCol-1)
        newPixel(i,j)=bigImage(i+m,j+n);
     end
    end
    %each 'new pixel' is used to histogrammically alter the 'smallImage'
    newHistSmall=histShape(smallImage,newPixel);
    %inner loop number 2 copies each pixel of the altered smallImg to the final Image
    for k=1:(pRow-1)
             for l=1:(pCol-1)
                %we copy our new (histogrammically correct) 'pixel' to our final
                %image
                 buffer(m+k,n+l)=newHistSmall(k,l);   
             end
     end         
    
    %end the two 'outer loops'
  end
end


%we convert the final image (a matrix of doubles) back to a uint8 greyscale
%image (for ease of print/output)
nImg=uint8(buffer);

end







%{
copied below is the entire histShape (and all it's included functions). We
did this because we were insure if we could be sure that the previously
written script would exist/be accessable from wherever you would be testing
our "createTiledImage". The code has been copied, including all comments
and references from Ex1
%}


%{
Function Name:histShape
Input: two image files (source, destination)
Output: new 'Histgramic Shape' image file
Function Operation: Create a new image from the source image
                    that matches the Histogram for the destination
                    image.
%}
function [newImg]= histShape(srcImg, destImg)
%we create histogramic arrays for both pictures
sourceHist=createHist(srcImg);
destHist=createHist(destImg);
%we create 'accumulated' hitogrammic arrays
sourceHist=accumulateHist(sourceHist);
destHist=accumulateHist(destHist);
%we normalize both the histogramic arrays
sourceHist=normalizeHist(sourceHist, srcImg);
destHist=normalizeHist(destHist, destImg);
%we find the conversion vector between the 2 (Normalized) Histograms
convertV=getConversionVector(sourceHist,destHist);
newImg=srcImg;
%we now alter each pixel in the source image according to the conversion
%vector we just defined
[srcRows,srcColumn]=size(srcImg);
for i=1:srcRows
            for j = 1:srcColumn
            newImg(i,j) = convertV(srcImg(i,j)+1);
            end
end
end



%{
Function Name:findHist
Input:image file
Output: histogramic array
Function Operation: Create a Histogrammic array for a received image
%}
function [tempHist] = createHist(img)
[Rows,Column] =size(img);
tempHist=zeros(1,256);
for i =1:Rows
    for j=1:Column
       pixel=(img(i,j)+1);
       tempHist(pixel)=(tempHist(pixel)+1);
    end
end
end

%{
Function Name:normalizeHist
Input: accumulated Histogramic array
Output: normalized histogramic array
Function Operation: Receive an (accumulated) Histogrammic array for
                    a received image then 'normalizes' the given
                    histogram.
%}
function [normHist]= normalizeHist(tempArray, tempImg)
[rows, column]=size(tempImg);
%we divide the accumulated histogram by the amount of 'pixels'
normHist=tempArray/(rows*column);
end

%{
Function Name:accumulateHist
Input: Histogramic array
Output: accumulated array
Function Operation: Receives a Histogrammic array image
                    then 'accumulates' the given histogram.
%}
function [accHist]= accumulateHist(tempHist)
accHist=tempHist;
for i=2:256
    accHist(i)=(accHist(i-1)+tempHist(i));
end
end

%{
Function Name:conversionVector
Input:two normalized Histogramic arrays
Output: conversion vector array
Function Operation: Receives 2 normalized Histogrammic arrays
                    then finds the 'conversion vector' between
                    the 2 arrays.
%}
function [conversionVector]= getConversionVector(srcNHist,dstNHist)
conversionVector=zeros(1,256);
s=1;
d=1;
while s<=256
    if dstNHist(d)<srcNHist(s)
        d= (d+1);
    else
        conversionVector(s) = mod(d, 256) + 1;
        s = s+1;
    end
end
end
